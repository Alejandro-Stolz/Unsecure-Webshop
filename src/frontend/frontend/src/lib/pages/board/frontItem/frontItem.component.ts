import {Component, Input} from "@angular/core";
import {Article} from "../../../data-access/models";

@Component({
  selector: 'front-item',
  templateUrl: './frontItem.component.html',
  styleUrls: ['./frontItem.component.scss']
})

export class FrontItemComponent {
  // @ts-ignore
  @Input() article: Article;
}