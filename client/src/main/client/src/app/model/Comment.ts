export class Comment {
  public author: string;
  public text: string;
  public replies: Comment[];
  public created: Date;
}
