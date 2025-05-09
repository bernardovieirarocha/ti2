package util;

import java.util.Arrays;
import java.util.List;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestSystemMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.azure.core.credential.AzureKeyCredential;

public class IA {

private String apiKey = "APIKEY AQUI";
private String endpoint = "END POINT AQUI";
    private String deploymentName = "chatgepete";
    

    public void consultarIA(String prompt) {

        String promptSystem = "Você é uma inteligencia artificial de teste para retornar informações para o cliente com base em suas perguntas.";

        String promptUser = prompt;

        System.out.println("==================== PROMPT ENVIADO À IA ====================");
        System.out.println("SYSTEM PROMPT: " + promptSystem);
        System.out.println("\nUSER PROMPT: " + promptUser);
        System.out.println("==============================================================");

        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endpoint)
                .buildClient();

        List<ChatRequestMessage> messages = Arrays.asList(
                new ChatRequestSystemMessage(promptSystem),
                new ChatRequestUserMessage(promptUser)
        );

        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(messages);
        chatCompletionsOptions.setMaxTokens(4096);
        chatCompletionsOptions.setTemperature(1d);
        chatCompletionsOptions.setTopP(1d);

        // Faz a requisição e obtém a resposta
        ChatCompletions chatCompletions = client.getChatCompletions(deploymentName, chatCompletionsOptions);

        System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());
        }
    }
}