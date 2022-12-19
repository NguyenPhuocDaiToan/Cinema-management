import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {SecurityRoutingModule} from './security-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { VerifyResetPasswordComponent } from './verify-reset-password/verify-reset-password.component';
import { VerificationComponent } from './verification/verification.component';



@NgModule({
  declarations: [LoginComponent, ResetPasswordComponent, VerifyResetPasswordComponent, VerificationComponent],
  exports: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    SecurityRoutingModule,
    ReactiveFormsModule,
  ]
})
export class SecurityModule { }
