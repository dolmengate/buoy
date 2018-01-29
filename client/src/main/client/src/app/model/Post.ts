export class Post {
  public id: number = null;
  public author: string = '';
  public title: string = '';
  public description: string = '';
  public created: Date = null;
  public lastModified: Date = null;
  public type: string = 'CODE';
  public version: number = 1.0;
  public comments: Comment[] = [];
  public get numComments(): number { return this.comments.length; };

  // fixme: Attachment vars - temporary
  contentText: string = '';
  editorText: string = '';
}
