import org.springframework.web.bind.annotation.*;

@RestController
public class VulnerableController {

    // LUKA 1: SQL Injection
    @GetMapping("/user")
    public String getUser(@RequestParam String id) {
        String sql = "SELECT * FROM users WHERE id = " + id;
        return sql;
    }

    // LUKA 2: Hardkodowany sekret
    private static final String API_KEY = "sk-1234567890abcdef";
    // LUKA 3: Brak autoryzacji
    @DeleteMapping("/admin/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Brak @PreAuthorize!
    }
}
