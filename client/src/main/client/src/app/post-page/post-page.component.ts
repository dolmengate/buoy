import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import axios from "axios";
import {Post} from "../model/Post";

@Component({
  selector: 'app-post',
  templateUrl: './post-page.component.html',
  styleUrls: ['./post-page.component.css']
})
export class PostPageComponent implements OnInit {

  public post: Post;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    let postId = this.route.snapshot.paramMap.get("id");
    axios.get(`/api/posts/${postId}`, { headers: { "Content-Type": "application/json"} })
      .then(res => this.post = res.data)
      .catch(err => console.log(err));
  }
}
