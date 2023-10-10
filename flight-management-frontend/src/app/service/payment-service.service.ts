import { Injectable } from '@angular/core';

declare var Razorpay: any;
@Injectable({
  providedIn: 'root',
})
export class PaymentServiceService {
  constructor() {}

  payNow(user: any, totalCost: number) {
    const RazorpayOptions = {
      description: 'Sample Razorpay demo',
      currency: 'INR',
      amount: totalCost * 100,
      name: 'Air Platina',
      key: 'rzp_test_Uh47k5lVlScFHI',
      image: 'https://i.imgur.com/FApqk3D.jpeg',
      prefill: {
        name: user.firstName + ' ' + user.lastName,
        phone: user.phoneNumber,
      },
      theme: {
        color: '#198754',
      },
      modal: {
        ondismiss: () => {
          console.log('dismissed');
        },
      },
    };

    const successCallback = (paymentid: any) => {
      console.log(paymentid);
    };

    const failureCallback = (e: any) => {
      console.log(e);
    };

    Razorpay.open(RazorpayOptions, successCallback, failureCallback);
  }
}
