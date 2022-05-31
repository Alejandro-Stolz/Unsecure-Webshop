import {Injectable} from "@angular/core";
import {BackendService} from "../backend.service";
import {Address} from "../../models";
import {ReplaySubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AddressStore {

  // @ts-ignore
  addresses: Address[];
  addressesSubject: ReplaySubject<Address[]> = new ReplaySubject<Address[]>(1);

  constructor(private backendService: BackendService) {
  }

  createAddress(address: Address): void {
    this.backendService.createAddress(address).subscribe(address => this.addresses.push(address));
  }

  loadAddressById(id: number): ReplaySubject<Address> {
    let addressSubject: ReplaySubject<Address> = new ReplaySubject<Address>();
    let index = this.addresses.findIndex(address => address.id === id);
    if (index === -1) {
      this.backendService.loadAddressById(id).subscribe(address => {
        addressSubject.next(address);
      });
    } else {
      addressSubject.next(this.addresses[index]);
    }
    return addressSubject;
  }

  loadAllAddresses(): ReplaySubject<Address[]> {
    if (!this.addresses) {
      this.backendService.loadAllAddresses().subscribe(addresses => {
        this.addresses = addresses;
        this.addressesSubject.next(this.addresses);
      });
    } else {
      this.addressesSubject.next(this.addresses);
    }
    return this.addressesSubject;
  }

  updateAddress(address: Address): void {
    let index = this.addresses.findIndex(item => item.id === address.id);
    this.addresses[index] = address;
    this.backendService.updateAddress(address).subscribe();
  }


}
