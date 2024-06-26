import { APP_INITIALIZER, ErrorHandler, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ContainerComponent } from './components/container/container.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { APP_SERVICE_CONFIG, APP_SERVICE_CONFIG_VALUE } from './AppConfig/appconfig.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RequestInterceptor } from './request.interceptor';
import { InitService } from './services/init.service';
import { RouterModule, Routes } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppNavComponent } from './app-nav/app-nav.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { HoverDirective } from './directives/hover.directive';
import { EmailvalidatorDirective } from './directives/emailvalidator.directive';
// import { RoomsModule } from './components/rooms/rooms.module';
import { HeaderModule } from './components/header/header.module';
import { RouteConfigToken } from './services/routeConfig.service';
import { ErrorhandlerService } from './errorhandler.service';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { SigninComponent } from './app-nav/sign/sign.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';

function initFactory(initService: InitService) {
  return () => initService.init();
}

@NgModule({
  declarations: [
    AppComponent,
    ContainerComponent,
    EmployeeComponent,
    AppNavComponent,
    NotFoundComponent,
    LoginComponent,
    HoverDirective,
    EmailvalidatorDirective,
    LoginComponent,
    SigninComponent
  ],
  imports: [
    BrowserModule,
    // RoomsModule,  // RoomsModule before AppRoutingModule to load the RoomsModule routes first and avoid the wildcard route
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    MatSnackBarModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDialogModule
    // HeaderModule,
  ],
  providers: [{
    provide: APP_SERVICE_CONFIG,
    useValue: APP_SERVICE_CONFIG_VALUE,
  },
  {
    provide: RouteConfigToken,
    useValue: { title: 'Hotel' },
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: RequestInterceptor,
    multi: true, // multi: true is used to register multiple interceptors
  },
  {
    provide: APP_INITIALIZER,
    useFactory: initFactory,
    deps: [InitService],
    multi: true,
  },
  {
    provide: ErrorHandler,
    useClass: ErrorhandlerService,
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
