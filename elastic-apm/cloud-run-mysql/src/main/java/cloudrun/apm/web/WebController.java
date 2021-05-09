package cloudrun.apm.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;


@RestController
public class WebController {


	private final JdbcTemplate jdbcTemplate;

	public WebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
   * Create an endpoint for the landing page
   *
   * @return the index view template
   */
  @GetMapping("/")
  public String helloWorld(Model model) {

    // Get Cloud Run environment variables.
    String revision = System.getenv("K_REVISION") == null ? "???" : System.getenv("K_REVISION");
    String service = System.getenv("K_SERVICE") == null ? "???" : System.getenv("K_SERVICE");

    // Set variables in html template.
    model.addAttribute("revision", revision);
    model.addAttribute("service", service);
    return "Welcome to Student Attendance Tracker!";
  }

	@GetMapping("/attendance")
	public List<String> getAttendance() {
		return this.jdbcTemplate.queryForList("SELECT * FROM attendance").stream()
				.map((m) -> m.values().toString())
				.collect(Collectors.toList());
    }
    
  @GetMapping("/attendance/_student/{id}")
	public List<String> getAttendanceByStudentList(@PathVariable("id") String id) {

		return this.jdbcTemplate.queryForList("SELECT * FROM attendance where id=?", id).stream()
				.map((m) -> m.values().toString())
				.collect(Collectors.toList());
    }
    
  @GetMapping("/courses")
	public List<String> getCourses() {
		return this.jdbcTemplate.queryForList("SELECT * FROM courses").stream()
				.map((m) -> m.values().toString())
				.collect(Collectors.toList());
    }

  @GetMapping("/students")
	public List<String> getStudents() {
		return this.jdbcTemplate.queryForList("SELECT * FROM students").stream()
				.map((m) -> m.values().toString())
				.collect(Collectors.toList());
    }
}