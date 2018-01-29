import { Component, OnInit } from '@angular/core';
import axios from "axios";
import { Post } from "../model/Post";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  public posts: Post[];

  constructor() { }

  ngOnInit() {
    axios.get('/api/posts')
      .then(res => this.posts = res.data)
      .catch(err => console.log(err));
  }
}
