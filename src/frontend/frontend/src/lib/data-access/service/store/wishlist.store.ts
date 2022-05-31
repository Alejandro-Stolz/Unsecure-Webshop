import {Injectable} from "@angular/core";
import {ReplaySubject} from "rxjs";
import {BackendService} from "../backend.service";
import {SpecifiedItem} from "../../models";

@Injectable({
  providedIn: 'root'
})
export class WishlistStore {
  // @ts-ignore
  specifiedItems: SpecifiedItem[];
  wishListSubject: ReplaySubject<SpecifiedItem[]> = new ReplaySubject<SpecifiedItem[]>(1);

  constructor(private backendService: BackendService) {

  }

  loadWishList(): ReplaySubject<SpecifiedItem[]> {
    if (this.specifiedItems == undefined) {
      this.backendService.loadWishList().subscribe(specifiedItems => {
        this.specifiedItems = specifiedItems;
        this.wishListSubject.next(this.specifiedItems);
      });
    } else {
      this.wishListSubject.next(this.specifiedItems)
    }
    return this.wishListSubject;
  }

  // updateItems(items: SpecifiedItem[]): ReplaySubject<SpecifiedItem[]> {
  //   this.specifiedItems = items;
  //   this.wishListSubject.next(this.specifiedItems);
  //   this.backendService.updateWishList(this.specifiedItems).subscribe();
  //   return this.wishListSubject;
  // }

  updateItem(itemId: number, newQuantity: number): ReplaySubject<SpecifiedItem[]> {
    let index = this.specifiedItems.findIndex(i => itemId == i.id);
    this.specifiedItems[index].quantity = newQuantity;
    this.wishListSubject.next(this.specifiedItems);
    this.backendService.updateWishListItem(this.specifiedItems[index]).subscribe();
    return this.wishListSubject;
  }

  addItem(item: SpecifiedItem): ReplaySubject<SpecifiedItem[]> {
    this.specifiedItems.push(item);
    this.wishListSubject.next(this.specifiedItems);
    this.backendService.updateWishList(this.specifiedItems).subscribe();
    return this.wishListSubject;
  }

  deleteItem(itemId: number): ReplaySubject<any> {
    let index = this.specifiedItems.findIndex(i => itemId == i.id);
    this.specifiedItems.splice(index, 1);
    this.wishListSubject.next(this.specifiedItems);
    this.backendService.deleteWishListItem(itemId).subscribe();
    return this.wishListSubject;
  }

  deleteWishlist(): ReplaySubject<any> {
    this.specifiedItems = [];
    this.wishListSubject.next(this.specifiedItems);
    this.backendService.deleteWishList().subscribe();
    return this.wishListSubject;
  }

}
