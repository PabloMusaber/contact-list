import { Component, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CompanyService } from 'src/app/services/company.service';
import { ModalService } from 'src/app/services/modal.service';
import { PeopleService } from 'src/app/services/people.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-contact-modal',
  templateUrl: './add-contact-modal.component.html',
  styleUrls: ['./add-contact-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AddContactModalComponent {
  constructor(
    private modalService: ModalService,
    private companyService: CompanyService,
    private peopleService: PeopleService
  ) { }

  idCompany: number;
  companyName: string;
  addContactForm: FormGroup;
  people = [];

  ngOnInit(): void {
    this.addContactForm = new FormGroup({
      contactId: new FormControl('', [])
    }, {
      updateOn: 'change'
    });

    this.peopleService.getPeople().subscribe({
      next: (subscribe) => {
        this.people = subscribe;
      },
      error: (error) => {
        console.log(error);
      }
    });

    this.companyService.getIdCompany().subscribe({
      next: (subscribe) => {
        this.idCompany = subscribe;
        this.companyService.getCompanyById(this.idCompany).subscribe({
          next: (subscribe) => {
            this.companyName = subscribe.name;
          },
          error: (error) => {
            console.log(error);
          }
        });
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  dismissModal() {
    this.modalService.dismissActiveModal();
  }

  addContact() {
    const contactId = this.addContactForm.get('contactId').value;

    this.companyService.addContactToCompany(this.idCompany, contactId).subscribe({
      next: (subscribe) => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Company added correctly!',
          showConfirmButton: false,
          timer: 1500
        });
        setTimeout(() => {
          window.location.reload();
        }, 1500);
      },
      error: (error) => {
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: error.error
        });
      }
    });



  }


}
