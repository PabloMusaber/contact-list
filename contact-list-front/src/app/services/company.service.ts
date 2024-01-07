import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  idCompany = new BehaviorSubject<number>(null);
  idCompany$ = this.idCompany.asObservable();


  companyURL = environment.apiUrl + 'api/companies/';

  constructor(private httpClient: HttpClient) { }

  createCompany(Company: any) {
    return this.httpClient.post(this.companyURL, Company);
  }

  getCompanies() {
    return this.httpClient.get<any>(this.companyURL);
  }

  getCompanyById(id: number) {
    return this.httpClient.get<any>(this.companyURL + id);
  }

  deleteCompany(id: number) {
    return this.httpClient.delete(this.companyURL + id);
  }

  addContactToCompany(idCompany: number, idPerson: number) {
    return this.httpClient.put(this.companyURL + idCompany + '/add-contact/' + idPerson, '');
  }

  saveIdCompany(id: number) {
    this.idCompany.next(id);
  }
  getIdCompany() {
    return this.idCompany$;
  }

}
