import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CompanyModalComponent } from './components/company-modal/company-modal.component';
import { PersonModalComponent } from './components/person-modal/person-modal.component';
import { HomeComponent } from './components/home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ContactsModalComponent } from './components/contacts-modal/contacts-modal.component';
import { SearchModalComponent } from './components/search-modal/search-modal.component';
import { AddContactModalComponent } from './components/add-contact-modal/add-contact-modal.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CompanyModalComponent,
    PersonModalComponent,
    HomeComponent,
    ContactsModalComponent,
    SearchModalComponent,
    AddContactModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
