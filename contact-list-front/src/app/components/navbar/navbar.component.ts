import { Component } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ModalService } from 'src/app/services/modal.service';
import { CompanyModalComponent } from '../company-modal/company-modal.component';
import { PersonModalComponent } from '../person-modal/person-modal.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent {

  constructor(
    private modalService: ModalService
  ) { }

  modalRef: NgbModalRef;

  ngOnInit() {

  }

  addPerson() {
    let modalInstance: any;
    this.modalRef = this.modalService.open(PersonModalComponent, {
      centered: true
    });
    modalInstance = this.modalRef.componentInstance;
  }

  addCompany() {
    let modalInstance: any;
    this.modalRef = this.modalService.open(CompanyModalComponent, {
      centered: true
    });
    modalInstance = this.modalRef.componentInstance;
  }

}