import {Component, Injectable, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AccountService} from '../service/account.service';


@Component({
  selector: 'app-account-login',
  templateUrl: './account-login.component.html',
  styleUrls: ['./account-login.component.css']
})
@Injectable()
export class AccountLoginComponent implements OnInit {
  formGroup: FormGroup;
  username: string;
  errorMessage = '';
  roles: string[] = [];

  constructor(
    private formBuild: FormBuilder,
    // private tokenStorageService: TokenStorageService,
    private accountService: AccountService,
    private router: Router,
    // private toastr: ToastrService,
    private route: ActivatedRoute,
    // private shareService: ShareService
    ) { }

  ngOnInit(): void {
    this.formGroup = this.formBuild.group({
        username: [''],
        password: ['']
      }
    );

    // if (this.tokenStorageService.getToken()) {
    //   const user = this.tokenStorageService.getUser();
    //   this.accountService.isLoggedIn = true;
    //   this.roles = this.tokenStorageService.getUser().roles;
    //   this.username = this.tokenStorageService.getUser().username;
    // }
  }



  submit() {
    this.accountService.login(this.formGroup.value).subscribe(
      data => {
        this.accountService.isLoggedIn = true;
        this.username = this.tokenStorageService.getUser().username;
        this.roles = this.tokenStorageService.getUser().roles;
        this.formGroup.reset();
        this.router.navigateByUrl(this.returnUrl);
        this.shareService.sendClickEvent();

      },
      err => {
        this.errorMessage = err.error.message;
        this.accountService.isLoggedIn = false;
        this.toastr.error("Sai tên đăng nhập hoặc mật khẩu hoặc tài khoản chưa được kích hoạt", "Đăng nhập thất bại: ", {
          timeOut: 3000,
          extendedTimeOut: 1500
        });

      }
    );
  }
}
