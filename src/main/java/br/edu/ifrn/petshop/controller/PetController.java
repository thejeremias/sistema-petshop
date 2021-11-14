package br.edu.ifrn.petshop.controller;

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
import br.edu.ifrn.petshop.dominio.Pet;

@RequestMapping("/pet")
@Controller
public class PetController {

	@GetMapping("/listar")
	public String listar(ModelMap model, HttpSession sessao) {
		model.addAttribute("pet", new Pet());
		model.addAttribute("pets", (List<Pet>) sessao.getAttribute("petsCadastrados"));
		return "pet/listar";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/salvar")
	public String salvar(@Valid Pet pet, BindingResult result, RedirectAttributes attr, HttpSession sessao) {
		if (result.hasErrors()) {
			if (pet.getId() == 0) {
				return "pet/listar";
			} else {
				return "pet/editar";
			}
		}
	    Integer id = (Integer) sessao.getAttribute("id");
	    List<Pet> petsCadastrados = (List<Pet>) sessao.getAttribute("petsCadastrados");
		if (id == null) {
			id = 1;
		}
		if (petsCadastrados == null) {
			petsCadastrados = new ArrayList<>();
		}
		if (pet.getId() == 0) {
			pet.setId(id);
			petsCadastrados.add(pet);
		    id++;	   
		    sessao.setAttribute("id", id);
		    sessao.setAttribute("petsCadastrados", petsCadastrados);
		    attr.addFlashAttribute("msgSucesso", "Pet cadastrado com sucesso!");
		} else {   
			petsCadastrados.remove(pet);
			petsCadastrados.add(pet);
		    attr.addFlashAttribute("msgSucesso", "Pet editado com sucesso!");   
		}   
			attr.addFlashAttribute("pet", new Pet());
			attr.addFlashAttribute("pets", petsCadastrados);
		return "redirect:/pet/listar";
	 }
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam(name = "nome", required = false) String nome, ModelMap model, HttpSession sessao) {
		List<Pet> petsCadastrados = (List<Pet>) sessao.getAttribute("petsCadastrados");
		List<Pet> petsEncontrados = new ArrayList();
		if (nome == null || nome.isEmpty()) {
			petsEncontrados = petsCadastrados;
		} else {
			if (petsCadastrados != null) {
				petsEncontrados = petsCadastrados.stream().filter(u -> u.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
			}
		}
		model.addAttribute("pet", new Pet());
		model.addAttribute("pets", petsEncontrados);
		return "pet/listar";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/editar/{id}")
	public String editar (@PathVariable("id") Integer id, ModelMap model, HttpSession sessao) {
		List<Pet> petsCadastrados = (List<Pet>) sessao.getAttribute("petsCadastrados");
		for (Pet pet : petsCadastrados) {
			if (pet != null) {
				if (pet.getId() == id) {
					model.addAttribute("pet", pet);
					break;
				}	
			}
		}
		return "pet/editar";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/deletar/{id}")
	public String deletar (
			@PathVariable("id") Integer id, HttpSession sessao, RedirectAttributes attr
			) {
		List<Pet> petsCadastrados = (List<Pet>) sessao.getAttribute("petsCadastrados");
		Pet p = new Pet();
		p.setId(id);
		boolean removeu = petsCadastrados.remove(p);
		if (removeu) {
			attr.addFlashAttribute("msgSucesso", "Pet removido com sucesso!");
		} else {
			attr.addFlashAttribute("msgErro", "Não foi possível remover o pet.");
		}
		return "redirect:/pet/listar";
	}
	
}
