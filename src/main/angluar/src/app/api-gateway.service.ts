import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {of} from "rxjs/observable/of";
import {catchError} from "rxjs/operators";


@Injectable()
export class ApiGatewayService {

  constructor(private _http: HttpClient) { }

  /** Get 방식 */
  get(url:string){

    return this._http.get<Response>(`${url}`)
        .delay(100)
        .pipe(
            catchError(this.handleError())
        )
  }

  /** Post 방식 */
  post(url: string, data: any){

      let httpHeaders = new HttpHeaders({
          'Content-type' : 'application/json'
      });

      return this._http.post(`${url}`,data,{headers : httpHeaders})
          .delay(100)
          .pipe(
            catchError(this.handleError())
          )
  }

  sendEmail(data){

      let httpHeaders = new HttpHeaders({
          'Content-type' : 'application/json'
      });

      return this._http.post(`https://api.emailjs.com/api/v1.0/email/send`,data,{headers : httpHeaders})
          .delay(100)
          .pipe(
              catchError(this.handleError())
          )
  }



    /** Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     *  */
    private handleError<T> (operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // send the error to remote logging infrastructure

            if(error instanceof HttpErrorResponse) {
                // Server or connection error happened
                if(!navigator.onLine) {
                    alert('No Internet connection');
                    // No Internet connection
                } else {
                    // Handle Http Error (error.status === 403, 404...)
                }
            } else {
                // Handle Client Error (angular Error, ReferenceError...)
            }

            // better job of transforming error for user consumption
            // this.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(error.error as T);
        };
    }

}
