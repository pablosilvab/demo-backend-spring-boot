package cl.pablosilvab.demobackendspringboot.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro que intercepta cada solicitud HTTP para validar un token JWT y autenticar al usuario.
 * Extiende {@link OncePerRequestFilter} para garantizar que se ejecute solo una vez por petición.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor del filtro JWT.
     *
     * @param jwtUtil            Utilidad para manejar tokens JWT.
     * @param userDetailsService Servicio para cargar detalles del usuario.
     */
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Intercepta cada petición HTTP y verifica si hay un token JWT válido en la cabecera "Authorization".
     * Si el token es válido, se autentica el usuario en el contexto de seguridad de Spring.
     *
     * @param request  Petición HTTP entrante.
     * @param response Respuesta HTTP saliente.
     * @param chain    Cadena de filtros de Spring Security.
     * @throws ServletException Si ocurre un error en la ejecución del filtro.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = extractJwtFromRequest(request);

        if (token != null && jwtUtil.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
            authenticateUser(token, request);
        }

        chain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT del encabezado "Authorization" en la solicitud HTTP.
     *
     * @param request Petición HTTP entrante.
     * @return Token JWT si está presente y bien formado, de lo contrario, null.
     */
    private String extractJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.substring(7) : null;
    }

    /**
     * Valida el token, extrae el usuario y establece la autenticación en el contexto de Spring Security.
     *
     * @param token   Token JWT válido.
     * @param request Petición HTTP actual.
     */
    private void authenticateUser(String token, HttpServletRequest request) {
        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
