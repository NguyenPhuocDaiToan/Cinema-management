import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';
import {TokenStorageService} from '../service/token-storage-service.service';
import {validate} from 'codelyzer/walkerFactory/walkerFn';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formGroup: FormGroup;
  username: string;
  errorMessage = '';
  roles: string[] = [];
  returnUrl: string;


  constructor(
    private formBuild: FormBuilder,
    private tokenStorageService: TokenStorageService,
    private authService: AuthenticationService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/';

    this.formGroup = this.formBuild.group({
        username: ['', [Validators.required]],
        password: ['', [Validators.required]]
      }
    );

    if (this.tokenStorageService.getToken()) {
      const user = this.tokenStorageService.getUser();
      this.authService.isLoggedIn = true;
      this.roles = this.tokenStorageService.getUser().roles;
      this.username = this.tokenStorageService.getUser().username;
    }
  }

  onSubmit() {
    this.authService.login(this.formGroup.value).subscribe(
      data => {
        console.log(data);
        // if (this.formGroup.value.remember_me) {
        this.tokenStorageService.saveTokenLocal(data.accessToken);
        this.tokenStorageService.saveUserLocal(data);
        // } else {
        //   this.tokenStorageService.saveTokenSession(data.accessToken);
        //   this.tokenStorageService.saveUserLocal(data);
        // }
        this.authService.isLoggedIn = true;
        this.username = this.tokenStorageService.getUser().username;
        this.roles = this.tokenStorageService.getUser().roles;
        this.formGroup.reset();
        this.router.navigateByUrl(this.returnUrl);
      },
      err => {
        this.errorMessage = err.error.message;
        this.authService.isLoggedIn = false;
        this.router.navigateByUrl('/security/login');
        alert(this.errorMessage);
      }
    );
  }

}
