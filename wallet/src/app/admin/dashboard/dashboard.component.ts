import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}

const today = new Date();
const month = today.getMonth();
const year = today.getFullYear();

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],

})
export class DashboardComponent implements OnInit {
  events = new FormControl();

  constructor() { }

  ngOnInit(): void {
  }
/*

  tiles: Tile[] = [
    {text: 'One', cols: 3, rows: 1, color: '#413968'},
    {text: 'Two', cols: 1, rows: 2, color: 'rgba(21,99,97,0.66)'},
    {text: 'Three', cols: 1, rows: 1, color: '#5e0d4a'},
    {text: 'Four', cols: 2, rows: 1, color: '#DDBDF1'},
  ];

  campaignOne = new FormGroup({
    start: new FormControl(new Date(year, month, 13)),
    end: new FormControl(new Date(year, month, 16)),
  });

  campaignTwo = new FormGroup({
    start: new FormControl(new Date(year, month, 15)),
    end: new FormControl(new Date(year, month, 19)),
  });
*/

}
