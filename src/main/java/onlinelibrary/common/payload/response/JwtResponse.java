package onlinelibrary.common.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    public JwtResponse(String jwt) {this.token = jwt;}
}

