Расчет прибыли (убытка) по валютному активу.

Приложение при запуске показывает окно (Swing) со следующими компонентами:

- Дата (Date) - дата покупки валюты
- Сумма в Долларах США (Amount USD) - количество купленных Долларов США
- Кнопка для запуска расчета (Recalculate)
- Не редактируемое поле для вывода полученной прибыли (убытка)

При нажатии на кнопку, приложение должно скачать курс обмена Доллара США на рубли из сервиса Fixer.io
(см. https://fixer.io/documentation) или любого другого сервиса по загрузке данных по курсам валют в формате JSON на
дату покупки и на текущую. Далее нужно рассчитать прибыль или убыток если бы валюту продали сегодня по текущему курсу.
При этом нужно учитывать помимо разницы курсов еще и спред (spread), равный 0.5% Прибыль выводится в рублях.

Приложение должно представлять из себя Maven или Gradle проект (Gradle в приоритете).

В проекте допускается использование сторонних библиотек (например для работы с JSON или выполнения HTTP-запросов).