package io.github.thejeremias.petshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.thejeremias.petshop.dominio.Produto;

@RequestMapping("/produto")
@Controller
public class ProdutoController {

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpSession sessao) {
		model.addAttribute("produto", new Produto());
		model.addAttribute("produtos", (List<Produto>) sessao.getAttribute("produtosCadastrados"));
		return "produto/listar";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/salvar")
	public String salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attr, HttpSession sessao) {
		if (result.hasErrors()) {
			if (produto.getId() == 0) {
				return "produto/listar";
			} else {
				return "produto/editar";
			}
		}  
	    Long id = (Long) sessao.getAttribute("id");
	    List<Produto> produtosCadastrados = (List<Produto>) sessao.getAttribute("produtosCadastrados");
		if (id == null) {
			id = 1L;
		}
		if (produtosCadastrados == null) {
			produtosCadastrados = new ArrayList<>();
		}
		if (produto.getId() == 0) {
			produto.setId(id);
			produtosCadastrados.add(produto);
		    id++;	   
		    sessao.setAttribute("id", id);
		    sessao.setAttribute("produtosCadastrados", produtosCadastrados);
		    attr.addFlashAttribute("msgSucesso", "Produto cadastrado com sucesso!");
		} else {   
			produtosCadastrados.remove(produto);
			produtosCadastrados.add(produto);
		    attr.addFlashAttribute("msgSucesso", "Produto editado com sucesso!");   
		}   
			attr.addFlashAttribute("produto", new Produto());
			attr.addFlashAttribute("produtos", produtosCadastrados);
			return "redirect:/produto/listar";
	 }
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam(name = "nome", required = false) String nome, ModelMap model, HttpSession sessao) {
		List<Produto> produtosCadastrados = (List<Produto>) sessao.getAttribute("produtosCadastrados");
		List<Produto> produtosEncontrados = new ArrayList();
		if (nome == null || nome.isEmpty()) {
			produtosEncontrados = produtosCadastrados;
		} else {
			if (produtosCadastrados != null) {
				produtosEncontrados = produtosCadastrados.stream().filter(u -> u.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
			}	
		}
		model.addAttribute("produto", new Produto());
		model.addAttribute("produtos", produtosEncontrados);
		return "produto/listar";
	}
		
	@SuppressWarnings("unchecked")
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, ModelMap model, HttpSession sessao) {
		List<Produto> produtosCadastrados = (List<Produto>) sessao.getAttribute("produtosCadastrados");
		for (Produto produto : produtosCadastrados) {
			if (produto != null) {
				if (produto.getId() == id) {
					model.addAttribute("produto", produto);
					break;
				}	
			}
		}
		return "produto/editar";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/deletar/{id}")
	public String deletar(@PathVariable("id") Long id, HttpSession sessao, RedirectAttributes attr) {
		List<Produto> produtosCadastrados = (List<Produto>) sessao.getAttribute("produtosCadastrados");
		Produto p = new Produto();
		p.setId(id);
		boolean removeu = produtosCadastrados.remove(p);
		if (removeu) {
			attr.addFlashAttribute("msgSucesso", "Produto removido com sucesso!");
		} else {
			attr.addFlashAttribute("msgErro", "Não foi possível remover o produto.");
		}
		return "redirect:/produto/listar";
	}
	
}
