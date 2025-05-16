import { useState } from 'react';

const UploadForm = () => {
    const [fileName, setFileName] = useState<string>('');

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (file) {
            setFileName(file.name);
        }
    };

    return (
        <form className="flex flex-col items-center space-y-5 w-full">
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
        </form>
    );
};

export default UploadForm;