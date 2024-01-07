import { Component, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ModalService } from 'src/app/services/modal.service';

@Component({
  selector: 'app-search-modal',
  templateUrl: './search-modal.component.html',
  styleUrls: ['./search-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class SearchModalComponent {

  constructor(
    private modalService: ModalService
  ) { }

  searchForm: FormGroup;
  search = {};

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      name: new FormControl('', []),
      city: new FormControl('', [])
    }, {
      updateOn: 'change'
    });
  }

  dismissModal() {
    this.modalService.dismissActiveModal();
  }

  searchPeople() {
    const name = this.searchForm.get('name').value;
    const city = this.searchForm.get('city').value;
    const cities = city.split(',');

    this.search = {
      name: name,
      city: city,
      cities: cities
    }
    this.dismissModal();

    // if (name && city === '') {
    //   // Buscar por nombre
    //   console.log("Busca por nombre");
    // } else if (name && cities.length > 0) {
    //   // Buscar por nombre y ciudades
    //   console.log("Busca por nombre y ciudad");
    // } else if (name === '' && cities.length === 1) {
    //   // Buscar por ciudad
    //   console.log("Busca por ciudad");
    // } else {
    //   // Caso por defecto o manejo adicional según tus necesidades
    //   console.log('Ingrese al menos un criterio de búsqueda');
    // }

  }

}
