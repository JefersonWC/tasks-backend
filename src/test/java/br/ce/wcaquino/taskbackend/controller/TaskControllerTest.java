package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	@Test
	public void naoDeveSalvarTarefasSemDescricao() throws ValidationException{
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		TaskController controller = new TaskController();
		controller.save(todo);			
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
	}
}

