import { Component, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CompanyService } from 'src/app/services/company.service';
import { ModalService } from 'src/app/services/modal.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-company-modal',
  templateUrl: './company-modal.component.html',
  styleUrls: ['./company-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class CompanyModalComponent {

  constructor(
    private modalService: ModalService,
    private companyService: CompanyService
  ) { }

  companyForm: FormGroup;

  ngOnInit(): void {
    this.companyForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      mobileNumber: new FormControl('', [Validators.required])
    }, {
      updateOn: 'change'
    });
  }

  dismissModal() {
    this.modalService.dismissActiveModal();
  }

  createCompany() {
    const Company = {
      name: this.companyForm.get('name').value,
      email: this.companyForm.get('email').value,
      mobileNumber: this.companyForm.get('mobileNumber').value,
    }

    this.companyService.createCompany(Company).subscribe({
      next: (response) => {
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
        console.log(error);
      }
    });
  }

}
