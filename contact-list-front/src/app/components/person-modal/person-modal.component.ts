import { Component, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ModalService } from 'src/app/services/modal.service';
import { PeopleService } from 'src/app/services/people.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-person-modal',
  templateUrl: './person-modal.component.html',
  styleUrls: ['./person-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class PersonModalComponent {

  constructor(
    private modalService: ModalService,
    private peopleService: PeopleService
  ) { }

  personForm: FormGroup;

  ngOnInit(): void {
    this.personForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      mobileNumber: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required])
    }, {
      updateOn: 'change'
    });
  }

  dismissModal() {
    this.modalService.dismissActiveModal();
  }

  createPerson() {
    const Person = {
      name: this.personForm.get('name').value,
      email: this.personForm.get('email').value,
      mobileNumber: this.personForm.get('mobileNumber').value,
      city: this.personForm.get('city').value,
    }

    this.peopleService.createPerson(Person).subscribe({
      next: (response) => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Person added correctly!',
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

}
