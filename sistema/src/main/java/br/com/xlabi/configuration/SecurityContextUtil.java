package br.com.xlabi.configuration;
//package br.com.xlabi.configuration;
//
//import java.security.Principal;
//
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public class SecurityContextUtil {
//
//	public static Principal getPrincipal() {
//		 
//		if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null
//				|| SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
//			System.out.println("getPrincipal null");
//			return null;
//		}
//	
//		if(AnonymousAuthenticationToken.class == SecurityContextHolder.getContext().getAuthentication().getClass()){
////			Principal principal = new Principal();
////			principal.setId("");
////			principal.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
//			return principal;
//		}else{
//			return (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		}
//	}
//	
//	public static void clear(){
//		SecurityContextHolder.clearContext();
//	}
//
//}
