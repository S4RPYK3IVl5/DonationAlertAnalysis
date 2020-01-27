import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DaAPIService} from '../../services/daAPI/da-api.service';

@Component({
  selector: 'app-code-consumtion',
  templateUrl: './code-consumption.component.html',
  styleUrls: ['./code-consumption.component.css']
})
export class CodeConsumptionComponent implements OnInit {

  code: string;

  constructor(private route: ActivatedRoute, private daAPIService: DaAPIService, private router: Router) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => this.code = params.code);
    this.daAPIService.codeConsumption(this.code).subscribe(
      data => console.log('data' + data),
      error => console.log('error' + error)
    );
    this.router.navigate(['/']);
  }

}
