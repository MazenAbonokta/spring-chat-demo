import { HttpHeaders, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { KeycloakService } from '../keycloak/keycloak.service';

export const keycloakHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const _keycloakService = inject(KeycloakService)
  const token= _keycloakService.keycloak.token;
  if(token){
    const authReq=req.clone({
      headers:new HttpHeaders({
        Authotization: `Beare ${token}`
      })
    });
    
  }
  return next(req);
};
