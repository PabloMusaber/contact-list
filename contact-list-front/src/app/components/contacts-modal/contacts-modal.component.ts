import { Component, ViewEncapsulation } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';
import { ModalService } from 'src/app/services/modal.service';

@Component({
  selector: 'app-contacts-modal',
  templateUrl: './contacts-modal.component.html',
  styleUrls: ['./contacts-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ContactsModalComponent {

  constructor(
    private modalService: ModalService,
    private companyService: CompanyService
  ) { }

  people = [];
  idCompany: number;

  ngOnInit() {
    this.companyService.getIdCompany().subscribe({
      next: (subscribe) => {
        this.idCompany = subscribe;
        this.companyService.getCompanyById(this.idCompany).subscribe({
          next: (subscribe) => {
            this.people = subscribe.contacts;
          },
          error: (error) => {
            console.log(error);
          }
        });
      },
      error: (error) => {

      }
    });
  }

  dismissModal() {
    this.modalService.dismissActiveModal();
  }

}
