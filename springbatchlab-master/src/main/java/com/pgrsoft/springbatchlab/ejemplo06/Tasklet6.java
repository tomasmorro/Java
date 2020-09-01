package com.pgrsoft.springbatchlab.ejemplo06;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Tasklet6 implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		//StepContribution -> Estado. Es Mutable
		//ChunkContext     -> Atributos compartidos entre invocaciones pero NO entre "reinicios"
		
		System.out.println(contribution);
		System.out.println("Tasklet6 en ejecución...");
		
		//Aquí se harían tareas de mantenimiento que no requieren leer ni escribir...
		
		return RepeatStatus.FINISHED;
	}

}
