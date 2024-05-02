import { Component, HostListener, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { Subject, takeUntil, Subscription } from "rxjs";
import { AuthService } from "src/app/services/auth.service";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "app-signin",
  templateUrl: "./sign.component.html",
  styleUrls: ["./sign.component.css"],
})
export class SigninComponent
  implements OnInit {
  authForm!: FormGroup;
  submitted = false;
  loading = false;
  error = "";
  hide = true;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = "";
  roles: string[] = [];

  passwordFlag: any;


  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) {
    this.router.onSameUrlNavigation = "reload";
  }

  ngOnInit() {
    this.authForm = this.formBuilder.group({
      username: ["", Validators.required],
      password: ["", Validators.required],
    });

    // this.tokenCookieService.deleteUser();
  }

  onSubmit(): void {
    console.log("Login details:", this.authForm.value);
    localStorage.clear();
   
    this.submitted = true;
    this.loading = true;
    this.error = '';
    if (this.authForm.invalid) {
       this.error = 'Username or Password not valid!';
       return;
    }
   
    this.authService.login(this.authForm.value).subscribe({
       next: (res: any) => {
         if (res.needsPasswordChange) {
           this.openChangePasswordDialog(res.email);
         } else {
           // Assuming res.body is available for the second snippet's logic
           if (res.body.statusCode === 200) {
            //  this.tokenCookieService.saveUser(res.body.entity);
             this.snackbar.open("snackbar-success", res.body.message);
             //this.router.navigate(["/authentication/otp-verification"]);

             //this.router.navigate(['/erp-dashboard/home']);
           } else {
             // Handle other status codes or lack of res.body.statusCode
             this.snackbar.open("snackbar-danger", "Login failed. Please try again.");
           }
         }
         this.loading = false;
       },
       error: (err: any) => {
         console.log("err::: ", err);
         if (err.status === 401) {
           this.snackbar.open("snackbar-danger", "Authentication failed: Please check your credentials and try again.");
         } else {
           this.snackbar.open("snackbar-danger", err.message);
         }
         this.loading = false;
       }
    });
   }   

 openChangePasswordDialog(email: string): void {
    // const dialogRef = this.dialog.open(ChangePasswordComponent, {
    //   width: '330px',
    //   height: '400px',
    //   data: { email: email } // Pass the email address to the dialog
    // });

    // dialogRef.afterClosed().subscribe(result => {
    //   this.router.navigate(['/erp-dashboard/home']);
    // });
 }
}
