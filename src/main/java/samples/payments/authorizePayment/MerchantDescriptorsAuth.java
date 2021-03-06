package samples.payments.authorizePayment;

import Api.PaymentApi;
import Invokers.ApiClient;
import Invokers.ApiException;
import Model.CreatePaymentRequest;
import Model.V2paymentsClientReferenceInformation;
import Model.V2paymentsMerchantInformation;
import Model.V2paymentsMerchantInformationMerchantDescriptor;
import Model.V2paymentsOrderInformation;
import Model.V2paymentsOrderInformationAmountDetails;
import Model.V2paymentsOrderInformationBillTo;
import Model.V2paymentsPaymentInformation;
import Model.V2paymentsPaymentInformationCard;

public class MerchantDescriptorsAuth {
	private static String responseCode = null;
	private static String responseMsg = null;

	static CreatePaymentRequest request;

	private static CreatePaymentRequest getRequest() {
		request = new CreatePaymentRequest();

		V2paymentsClientReferenceInformation client = new V2paymentsClientReferenceInformation();
		client.code("1234567890");
		request.clientReferenceInformation(client);
		
		V2paymentsOrderInformationBillTo billTo = new V2paymentsOrderInformationBillTo();
		billTo.country("US");
		billTo.firstName("John");
		billTo.lastName("Deo");
		billTo.phoneNumber("6504327113");
		billTo.address2("Desk M3-5573");
		billTo.address1("901 Metro Center Blvd");
		billTo.postalCode("94404");
		billTo.locality("Foster City");
		billTo.company("Visa");
		billTo.administrativeArea("CA");
		billTo.email("test@cybs.com");

		V2paymentsOrderInformationAmountDetails amountDetails = new V2paymentsOrderInformationAmountDetails();
		amountDetails.totalAmount("72210");
		amountDetails.currency("USD");

		V2paymentsOrderInformation orderInformation = new V2paymentsOrderInformation();
		orderInformation.billTo(billTo);
		orderInformation.amountDetails(amountDetails);
		request.setOrderInformation(orderInformation);

		V2paymentsPaymentInformationCard card = new V2paymentsPaymentInformationCard();
		card.expirationYear("2031");
		card.number("4111111111111111");
		card.securityCode("123");
		card.expirationMonth("12");
		
		V2paymentsMerchantInformationMerchantDescriptor merchantDescriptor=new V2paymentsMerchantInformationMerchantDescriptor();
		merchantDescriptor.name("TestDescriptionInformation");
		merchantDescriptor.contact("123-456-7890");
		
		V2paymentsMerchantInformation merchantInformation=new V2paymentsMerchantInformation();
		merchantInformation.merchantDescriptor(merchantDescriptor);
		
		V2paymentsPaymentInformation paymentInformation = new V2paymentsPaymentInformation();
		paymentInformation.card(card);
		request.setPaymentInformation(paymentInformation);

		return request;

	}
	public static void main(String args[]) throws Exception {
		new MerchantDescriptorsAuth();
	}

	public MerchantDescriptorsAuth() throws Exception {
		process();
	}
	

	public static void process() throws Exception {

		try {
			request = getRequest();

			PaymentApi paymentApi = new PaymentApi();
			paymentApi.createPayment(request);

			responseCode = ApiClient.resp;
			responseMsg = ApiClient.respmsg;
			System.out.println("ResponseCode :" + responseCode);
			System.out.println("ResponseMessage :" + responseMsg);

		} catch (ApiException e) {

			e.printStackTrace();
		}
	}

}
