import { useState } from "react";
import axios from "axios";

const UploadForm = () => {
    const [fileName, setFileName] = useState<string>("");
    const [file, setFile] = useState<File | null>(null);
    const [result, setResult] = useState<any>(null);
    const [error, setError] = useState<string | null>(null);

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const selected = e.target.files?.[0];
        if (selected) {
            setFile(selected);
            setFileName(selected.name);
            setError(null);
        }
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!file) return;

        const formData = new FormData();
        formData.append("file", file);

        try {
            const response = await axios.post("http://localhost:8080/api/upload", formData, {
                headers: { "Content-Type": "multipart/form-data" },
            });

            setResult(response.data);
        } catch (err: any) {
            setError("Ошибка при отправке файла. Проверь подключение к backend.");
            console.error(err);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col items-center space-y-5 w-full">
            <label className="w-full text-sm font-medium text-gray-700">
                Выберите Excel-файл (.xlsx):
                <input
                    type="file"
                    accept=".xlsx"
                    onChange={handleFileChange}
                    className="mt-2 block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4
                     file:rounded-md file:border-0 file:text-sm file:font-semibold
                     file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"
                />
            </label>

            {fileName && <p className="text-xs text-gray-600">Выбранный файл: {fileName}</p>}

            <button
                type="submit"
                className="w-full py-2 px-4 bg-blue-600 hover:bg-blue-700 text-white font-semibold rounded-md shadow-md transition active:scale-95"
            >
                Загрузить и проанализировать
            </button>

            {error && <p className="text-red-500 text-sm">{error}</p>}

            {result && (
                <div className="text-sm text-left text-gray-800 w-full mt-4 bg-white bg-opacity-60 p-4 rounded-xl">
                    <h2 className="text-lg font-bold mb-2">Результат анализа:</h2>
                    <ul className="list-disc pl-5">
                        <li>Всего студентов: {result.totalStudents}</li>
                        <li>Заморожены: {result.frozenCount}</li>
                        <li>Заблокированы: {result.blockedCount}</li>
                        <li>С просрочкой: {result.overdueCount}</li>
                        <li>Не достигли цели: {result.belowTargetCount}</li>
                    </ul>
                </div>
            )}
        </form>
    );
};

export default UploadForm;
