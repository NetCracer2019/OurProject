import { Component, OnInit } from '@angular/core';
import {Menu} from "../_models/Menu";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {


  Menu = [
    new Menu("Главная", "/home"),
    new Menu("Книги", "/books"),
    new Menu("Мой профиль", "/profile"),
    new Menu("Друзья", "/friends"),
    new Menu("Рекомендации", "/recommendations"),
    new Menu("Чат", "/chat"),
  ];

  constructor() { }

  ngOnInit() {
  }

}
