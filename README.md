При запуске параметрами командной строки передаётся нечётное число >=3 неповторяющихся строк.
Эти строки — это ходы (например, Камень Ножницы Бумага или Камень Ножницы Бумага Ящерица Спок). 
Победа определяется так — половина следующих выигрывает, половина предыдущих проигрывает (по кругу).
Скрипт генерирует случайный ключ длиной 128 бит, делает свой ход, вычисляет HMAC (на базе SHA2 или SHA3) от хода со сгенерированным ключом, показывает пользователю HMAC. 
После этого пользователь получает "меню" 1 - Камень, 2 - Ножницы, ...., 0 - Exit. Пользователь делает свой выбор (при некорректном вводе опять отображается "меню"). 
Скрипт показывает кто победил, ход компьютера и исходный ключ.
Таким образом, пользователь может проверить, что компьютер играет честно (не поменял свой ход после хода пользователя).
