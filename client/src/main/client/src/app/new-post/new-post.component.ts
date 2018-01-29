import { Component, OnInit } from '@angular/core';
import {Post} from "../model/Post";
import axios from "axios";

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  TYPES: string[] = ['CODE', 'TEXT'];
  post: Post;

  constructor() { }

  ngOnInit() {
    this.post = new Post();
  }

  onSubmit() {
    axios.post('/api/posts/new', this.post)
      .then(res => console.log(res))
      .catch(err => console.log(err));
  }
}
