package br.edu.ifrn.petshop.dominio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Produto {

	private int id;
	
	@Size(min = 3, message = "O campo Nome deve receber ao menos 3 caracteres.")
	@NotBlank(message = "O campo Nome não pode estar em branco.")
	private String nome;
	
	@Size(min = 3, message = "O campo Código deve receber ao menos 3 caracteres.")
	@NotBlank(message = "O campo Código não pode estar em branco.")
	private String codigo;
	
	/*A validação deste campo foi feita toda pelo HTML mesmo*/
	private int quantidade;
	
}
