Feature: Avaliacao

Scenario: Validar dados do json

Given que estou na home
When eu clicar no menu Guide
And clicar no menu fotos
Then deverá validar o id 6 com o titulo "accusamus ea aliquid et amet sequi nemo" , url "https://via.placeholder.com/600/56a8c2" e thumbnailUrl "https://via.placeholder.com/150/56a8c2"
And salvar em um arquivo json