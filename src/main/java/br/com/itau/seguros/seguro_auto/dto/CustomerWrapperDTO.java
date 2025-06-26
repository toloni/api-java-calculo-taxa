package br.com.itau.seguros.seguro_auto.dto;

public class CustomerWrapperDTO {

    private CustomerDTO customer;

    public CustomerWrapperDTO() {
    }

    public CustomerWrapperDTO(CustomerDTO customer) {
        this.customer = customer;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
