import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class representing Lalithadithya's Developer Portfolio.
 */
public class Main {

    public static void main(String[] args) {
        DeveloperProfile profile = createProfile();
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("   LALITHADITHYA M - PORTFOLIO SYSTEM   ");
            System.out.println("=========================================");
            System.out.println("1. View Full Profile");
            System.out.println("2. View Technical Skills & Stack");
            System.out.println("3. View Featured Projects");
            System.out.println("4. Export Profile to Markdown File");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    profile.displayFullProfile();
                    break;
                case "2":
                    profile.displaySkills();
                    break;
                case "3":
                    profile.displayProjects();
                    break;
                case "4":
                    profile.exportToMarkdown("portfolio_profile.md");
                    break;
                case "5":
                    System.out.println("Exiting application. Have a great day!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static DeveloperProfile createProfile() {
        List<String> roles = Arrays.asList("Backend Developer", "UI/UX Designer");
        List<String> languages = Arrays.asList("Java", "Python", "JavaScript");
        List<String> techSkills = Arrays.asList("Node.js", "HTML/CSS", "Figma", "REST APIs", "Git/GitHub");

        DeveloperProfile profile = new DeveloperProfile(
                "LALITHADITHYA M",
                "2nd Year Student",
                "Information Technology",
                "profile.jpg",
                roles,
                languages,
                techSkills
        );

        profile.addProject(new Project(
                "Full-Stack Portfolio",
                "A clean, responsive personal portfolio showcasing projects and skills.",
                Arrays.asList("JavaScript", "HTML", "CSS", "Node.js")
        ));

        profile.addProject(new Project(
                "Backend API & Services",
                "Scalable server-side logic and API implementation built using Node.js and Java.",
                Arrays.asList("Node.js", "Java", "REST API")
        ));

        profile.addProject(new Project(
                "UI/UX Design Systems",
                "Interactive user flow prototypes, wireframes, and modern visual design solutions.",
                Arrays.asList("Figma", "UI/UX", "Prototyping")
        ));

        return profile;
    }
}

/**
 * Model class representing a Developer's Profile.
 */
class DeveloperProfile {
    private String name;
    private String year;
    private String field;
    private String imagePath;
    private List<String> roles;
    private List<String> languages;
    private List<String> techSkills;
    private List<Project> projects;

    public DeveloperProfile(String name, String year, String field, String imagePath,
                            List<String> roles, List<String> languages, List<String> techSkills) {
        this.name = name;
        this.year = year;
        this.field = field;
        this.imagePath = imagePath;
        this.roles = roles;
        this.languages = languages;
        this.techSkills = techSkills;
        this.projects = new ArrayList<>();
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void displayFullProfile() {
        System.out.println("\n-----------------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Status: " + year + " (" + field + ")");
        System.out.println("Roles: " + String.join(", ", roles));
        System.out.println("Profile Image: " + imagePath);
        System.out.println("-----------------------------------------");
        displaySkills();
        displayProjects();
    }

    public void displaySkills() {
        System.out.println("\n--- TECHNICAL SKILLS ---");
        System.out.println("Programming Languages: " + String.join(", ", languages));
        System.out.println("Technologies & Tools:  " + String.join(", ", techSkills));
    }

    public void displayProjects() {
        System.out.println("\n--- FEATURED PROJECTS ---");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i));
        }
    }

    public void exportToMarkdown(String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(name).append("\n\n");
        sb.append("![Profile Image](").append(imagePath).append(")\n\n");
        sb.append("**").append(year).append(" | ").append(field).append("**  \n");
        sb.append("**Roles:** ").append(String.join(" | ", roles)).append("\n\n");
        
        sb.append("## Technical Skills\n");
        sb.append("- **Languages:** ").append(String.join(", ", languages)).append("\n");
        sb.append("- **Technologies:** ").append(String.join(", ", techSkills)).append("\n\n");

        sb.append("## Projects\n");
        for (Project p : projects) {
            sb.append("### ").append(p.getTitle()).append("\n");
            sb.append(p.getDescription()).append("\n");
            sb.append("**Tech Stack:** ").append(String.join(", ", p.getTechStack())).append("\n\n");
        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(sb.toString());
            System.out.println("\nSuccessfully exported profile to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}

/**
 * Model class representing a Project.
 */
class Project {
    private String title;
    private String description;
    private List<String> techStack;

    public Project(String title, String description, List<String> techStack) {
        this.title = title;
        this.description = description;
        this.techStack = techStack;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTechStack() {
        return techStack;
    }

    @Override
    public String toString() {
        return title + " [" + String.join(", ", techStack) + "]\n   " + description;
    }
}