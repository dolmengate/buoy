import {Comment} from "./Comment";

export class Post {
  public id: number;
  public author: string;
  public title: string;
  public description: string;
  public created: Date;
  public lastModified: Date;
  public type: string;
  public version: number;
  public comments: Comment[];
  public get numComments(): number { if (this.comments === null) return 0; return this.comments.length; };

  // fixme: Attachment vars - temporary
  contentText: string = '';
  editorText: string;
}
