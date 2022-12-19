import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  formGroup: FormGroup;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  isSubmited = false;
  formValid = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthenticationService,
    // private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      username: ['', [Validators.required]]
    });
  }

  onSubmit() {
    this.isSubmited = true;
    // @ts-ignore
    this.authService.resetPassword(this.formGroup.value.username).subscribe(
      data => {
    //     this.toastr.success('Email đã được gửi!', 'Thành công: ', {
    //       timeOut: 2500,
    //       extendedTimeOut: 1500
    //     });
    this.router.navigateByUrl('/security/verify-reset-password');
      },
      err => {
        // this.toastr.error('Sai tên đăng nhập hoặc tên đăng nhập chưa được đăng ký', 'Gửi email thất bại: ', {
        //   timeOut: 3000,
        //   extendedTimeOut: 1500
        // });
        alert('lỗi');
        this.router.navigateByUrl('/security/reset-password');
      },
      // @ts-ignore
        com => {
        this.router.navigateByUrl('/');
      }
    );
  }

}
