import { Component } from '@angular/core';
import { ModalService } from 'src/app/services/modal.service';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';
import { ContactsModalComponent } from '../contacts-modal/contacts-modal.component';
import { SearchModalComponent } from '../search-modal/search-modal.component';
import { AddContactModalComponent } from '../add-contact-modal/add-contact-modal.component';
import { CompanyService } from 'src/app/services/company.service';
import { PeopleService } from 'src/app/services/people.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent {

  constructor(
    private modalService: ModalService,
    private peopleService: PeopleService,
    private companyService: CompanyService
  ) { }

  modalRef: NgbModalRef;
  companies = [];
  people = [];
  search = {};


  ngOnInit() {
    this.peopleService.getPeople().subscribe({
      next: (response) => {
        this.people = response;
      },
      error: (error) => {
        console.log(error);
      }
    });

    this.companyService.getCompanies().subscribe({
      next: (response) => {
        this.companies = response;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  viewContacts(id: number) {
    this.companyService.saveIdCompany(id);
    let modalInstance: any;
    this.modalRef = this.modalService.open(ContactsModalComponent, {
      centered: true
    });
    modalInstance = this.modalRef.componentInstance;
  }

  searchPeople() {
    let modalInstance: any;
    this.modalRef = this.modalService.open(SearchModalComponent, {
      centered: true
    });
    modalInstance = this.modalRef.componentInstance;
    this.modalRef.dismissed.subscribe(data => {
      this.search = modalInstance.search;
      this.searchFilterPeople();
    })
  }

  searchFilterPeople() {
    console.log(this.search['name'], this.search['city'], this.search['cities']);
    if (this.search['name'] && this.search['city'] === '') {
      this.peopleService.getPeopleByName(this.search['name']).subscribe({
        next: (subscribe) => {
          this.people = subscribe;
        },
        error: (error) => {
          console.log(error);
        }
      });

    } else if (this.search['name'] && this.search['cities'].length > 0) {
      this.peopleService.getPeopleByNameAndCities(this.search['name'], this.search['cities']).subscribe({
        next: (subscribe) => {
          this.people = subscribe;
        },
        error: (error) => {
          console.log(error);
        }
      });

    } else if (this.search['name'] === '' && this.search['city']) {
      this.peopleService.getPeopleByCity(this.search['city']).subscribe({
        next: (subscribe) => {
          this.people = subscribe;
        },
        error: (error) => {
          console.log(error);
        }
      });

    } else if (this.search['name'] === '' && this.search['city'] === '') {
      this.ngOnInit();

    } else {
      console.log('Ingrese al menos un criterio de búsqueda');
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Enter a valid search criteria"
      });
    }
  }

  addContact(id: number) {
    this.companyService.saveIdCompany(id);
    let modalInstance: any;
    this.modalRef = this.modalService.open(AddContactModalComponent, {
      centered: true
    });
    modalInstance = this.modalRef.componentInstance;
  }

  deleteCompany(id: number) {
    Swal.fire({
      title: "Are you sure you want to eliminate the company?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.companyService.deleteCompany(id).subscribe({
          next: (response) => {
            Swal.fire({
              title: "Deleted!",
              text: "The contact has been deleted.",
              icon: "success",
              showConfirmButton: false,
              timer: 1500
            });
            setTimeout(() => {
              window.location.reload();
            }, 1500);
          },
          error: (error) => {
            console.log(error);
          }
        });
      }
    });
  }

}