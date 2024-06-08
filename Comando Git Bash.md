## Comandos GitHub
- Logar no usuário\
git config --global user.name "John Doe"\
git config --global user.email johndoe@example.com

- Excluir informação no config
git config --global --unset-all "user.mail"

- Colocar o codigo no repositorio \
git init \
git add . \
git commit -m "Mensagem explicativa" \
git branch -M codigo-fonte \
git remote add origin https://github.com/Viniciussfernandes/Projeto-Integrador \
git push -u origin codigo-fonte

- Modificar repositorio \
git pull origin (branch correspondente) \
git add . \
git commit -m "Mensagem explicativa" \
git push

- Resolver push rejeitado, a ideia é sincronizar o historico do repositorio com o da maquina\
git pull origin (branch correspondente) --allow-unrelated-histories

- Historico de commit \
git log\
git log --oneline
