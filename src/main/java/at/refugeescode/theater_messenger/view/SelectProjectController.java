package at.refugeescode.theater_messenger.view;

import at.refugeescode.theater_messenger.controller.ProjectController;
import at.refugeescode.theater_messenger.persistence.model.Actor;
import at.refugeescode.theater_messenger.persistence.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/editproject")
public class SelectProjectController {

    private ProjectController projectController;

    public SelectProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }

    @PostMapping("/{projectId}")
    String newActor(@PathVariable Long projectId, Actor actor) {
        projectController.addNewActor(projectId, actor);
        System.out.println("----ADD NEW ACROT");
        return "redirect:/editproject/?id=" + projectId;
    }

    @ModelAttribute("newActor")
    Actor newActor() {
        return new Actor();
    }

    @GetMapping(params = {"id"})
    String page(@RequestParam("id") Long id, Model model) {
//        Optional<Project> project = projectController.findProject(id);
        model.addAttribute("project", projectController.findProject(id).get());
        model.addAttribute("actors", projectController.showAllActors(id));
        System.out.println("===ID//" + id);
        return "editproject";
    }

    @RequestMapping(value = "actor", params = {"projectId", "actorId"}, method = GET)
    String deleteActor(@RequestParam("projectId") Long projectId, @RequestParam("actorId") Long actorId, Model model) {
        Optional<Project> project = projectController.findProject(projectId);
        model.addAttribute("project", project.get());
        model.addAttribute("actors", projectController.showAllActors(projectId));
        projectController.deleteActor(projectId, actorId);
        return "redirect:/editproject/?id=" + projectId;
    }

    @RequestMapping(value = "project", params = {"projectId"}, method = GET)
    String deleteProject(@RequestParam("projectId") Long projectId, Model model) {
        Optional<Project> project = projectController.findProject(projectId);
        model.addAttribute("project", project.get());
        model.addAttribute("actors", projectController.showAllActors(projectId));
        projectController.deleteProject(projectId);
        return "redirect:/engineer";
    }
}