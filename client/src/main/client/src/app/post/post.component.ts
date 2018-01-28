import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import axios from "axios";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    let postId = this.route.snapshot.paramMap.get("id");
    axios.get(`/api/posts/${postId}`, { headers: { "Content-Type": "application/json"} })
      .then(res => console.log(res.data))
      .catch(err => console.log(err));
    // fetch(`/api/posts/${postId}`, { headers })
    //   .then(res => console.log(res));
  }
}
