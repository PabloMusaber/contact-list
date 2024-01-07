import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  peopleURL = environment.apiUrl + 'api/people/';

  constructor(private httpClient: HttpClient) { }

  createPerson(Person: any) {
    return this.httpClient.post(this.peopleURL, Person);
  }

  getPeople() {
    return this.httpClient.get<any>(this.peopleURL);
  }

  getPeopleByName(name: string) {
    return this.httpClient.get<any>(this.peopleURL + 'find-by-name/' + name);
  }

  getPeopleByCity(city: string) {
    return this.httpClient.get<any>(this.peopleURL + 'find-by-city/' + city);
  }

  getPeopleByNameAndCities(name: string, cities: string[]) {
    const params = new HttpParams().set('cities', cities.join(','));
    return this.httpClient.get<any>(this.peopleURL + 'find-by-name/' + name, { params });
  }

}
