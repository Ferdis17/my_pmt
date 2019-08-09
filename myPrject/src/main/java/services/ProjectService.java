package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Project;
import exceptions.ProjectIdException;
import repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		// some logic should be added
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch (Exception ex) {
			throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "doesn't exist");
		}
		
	}
	
	public Project findByProjectIdentifier(String projectId) {
		
		Project  project = projectRepository.findbyProjectIdentifier(projectId.toUpperCase());
		 if(project == null) {
			 throw new ProjectIdException("Project Id' " + projectId + " doesn't exist");
		 }
		 return project;
	}
	
	public Iterable<Project>findAllProjects(){
		return projectRepository.findAll();	
		}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findbyProjectIdentifier(projectId);
		if(project ==  null) {
			throw new ProjectIdException("cannot delete project with ID ' " +projectId+"'. This project does not exist");
		}
		projectRepository.delete(project);
	}

}
