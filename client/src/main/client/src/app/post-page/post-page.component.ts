import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import axios from "axios";
import {Post} from "../model/Post";

@Component({
  selector: 'app-post-page',
  templateUrl: './post-page.component.html',
  styleUrls: ['./post-page.component.css']
})
export class PostPageComponent implements OnInit {

  public post: Post;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    const postId = this.route.snapshot.paramMap.get("id");
    axios.get(`/api/posts/${postId}`, { headers: { "Content-Type": "application/json"} })
      .then(res => {
        console.log('data', res.data);
        this.post = this.formatPostDates(res.data);
      })
      .catch(err => console.log(err));
  }

  saveAndIncrement() {
    axios.post(`/api/posts/save/${this.post.id}`, { headers: { "Content-Type": "application/json"} })
      .then(res => {
        console.log('data', res.data);
        this.post = this.formatPostDates(res.data);
      })
      .catch(err => console.log(err));
  }

  private formatPostDates(postData: any): Post {
    postData.created = new Date(postData.created.nano);
    postData.lastModified = new Date(postData.lastModified.nano);

    postData.comments.forEach(comment => {
      comment.created = new Date(comment.created.nano)
    });
    return postData;
  }
}
