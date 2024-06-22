package tech.buildrun.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidCpfCnpjException extends PicPayException{
    private final String detail;

    public InvalidCpfCnpjException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet CpfCnpj not valid");
        pb.setDetail(detail);

        return pb;
    }
}
