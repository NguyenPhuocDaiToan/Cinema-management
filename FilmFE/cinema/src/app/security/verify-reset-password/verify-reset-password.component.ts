import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';

@Component({
  selector: 'app-verify-reset-password',
  templateUrl: './verify-reset-password.component.html',
  styleUrls: ['./verify-reset-password.component.css']
})
export class VerifyResetPasswordComponent implements OnInit {

  isSuccessful = true;
  isSendMail: boolean;
  isSubmited: true;
  code: string;

  formGroup: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthenticationService,
    private formBuilder: FormBuilder,
    // private toastr: ToastrService,
    private router: Router
  ) { }

  // tslint:disable-next-line:variable-name
  validation_messages = {
    password: [
      {type: 'required', message: 'Trường này không được để trống!'},
      {type: 'minlength', message: 'Mật khẩu cần nhiều hơn 8 ký tự'},
      {type: 'maxlength', message: 'Mật khẩu chỉ được ít hơn 32 ký tự'},
    ]
  };

  ngOnInit(): void {

    this.formGroup = this.formBuilder.group({
      newPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(32)]],
      repeatNewPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(32)]],
    });
    this.route.queryParams.subscribe(params => {
      const code = params.code;
      if (code == null) {
        this.isSendMail = false;
      } else {
        this.isSendMail = true;
        this.isSuccessful = false;
        this.authService.verifyPassword(code).subscribe(
          data => {
            this.isSuccessful = (data.message === 'accepted');
          },
          err => {
            this.isSuccessful = false;
            alert('đổi mật khẩu thất bại');
          }
        );
      }
    });
  }

  onSubmit() {
    if (this.formGroup.value.newPassword === this.formGroup.value.repeatNewPassword) {
      this.route.queryParams.subscribe(params => {
        this.code = params.code;
      });
      this.authService.doResetPassword(this.formGroup.value.newPassword, this.code).subscribe(data => {
        // this.toastr.success('Mật khẩu đã được thay đổi!',"Thành công");
        alert('đổi mật khẩu thành công');
        this.router.navigateByUrl('/security/login');
      });
    } else {
      // this.toastr.error('Trường nhập lại mật khẩu và mật khẩu không giống nhau!', 'Lỗi: ', {
      //   timeOut: 3500,
      //   extendedTimeOut: 1500
      // });
    }
  }
}
